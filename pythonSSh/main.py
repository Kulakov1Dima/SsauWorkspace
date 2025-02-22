import time
import threading
import asyncio
import paramiko
from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from fastapi.responses import HTMLResponse

app = FastAPI()

html = """
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>SSH Консоль (FastAPI)</title>
  <style>
    body { background: #222; color: #eee; font-family: monospace; }
    #output {
      background: #000;
      padding: 10px;
      height: 400px;
      overflow-y: auto;
      border: 1px solid #555;
      white-space: pre-wrap;
    }
    #input {
      width: 100%;
      padding: 10px;
      font-family: monospace;
      font-size: 14px;
      box-sizing: border-box;
      background: #111;
      color: #eee;
      border: 1px solid #555;
    }
  </style>
</head>
<body>
  <h1>SSH Консоль (FastAPI)</h1>
  <div id="output"></div>
  <input type="text" id="input" placeholder="Введите команду и нажмите Enter">
  <script src="https://cdn.jsdelivr.net/npm/ansi_up@4.0.4/ansi_up.js"></script>
  <script>
    var ws = new WebSocket("ws://" + location.host + "/ws");
    const ansi_up = new AnsiUp();
    ansi_up.use_classes = true;

    ws.onmessage = function(event) {
      var output = document.getElementById("output");
      var line = document.createElement("div");
      line.innerHTML = ansi_up.ansi_to_html(event.data);
      output.appendChild(line);
      output.scrollTop = output.scrollHeight;
    };

    document.getElementById("input").addEventListener("keydown", function(e) {
      if (e.key === "Enter") {
        var command = this.value.trim();
        if (command) {
          ws.send(command);
          var output = document.getElementById("output");
          var userInput = document.createElement("div");
          userInput.innerHTML = `<span style="color: lightgreen;">$ ${command}</span>`;
          output.appendChild(userInput);
        }
        this.value = "";
      }
    });
  </script>
</body>
</html>
"""

@app.get("/")
async def get():
    return HTMLResponse(html)

class SSHManager:
    def __init__(self):
        self.client = None
        self.channel = None
        self.thread = None

    def connect(self, hostname: str, port: int, username: str, password: str):
        self.client = paramiko.SSHClient()
        self.client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        self.client.connect(hostname=hostname, port=port, username=username, password=password)
        self.channel = self.client.invoke_shell()

    def send_command(self, command: str):
        if self.channel:
            self.channel.send(command + "\n")

    def start_reading(self, websocket: WebSocket, loop: asyncio.AbstractEventLoop):
        self.thread = threading.Thread(target=self.read_output, args=(websocket, loop), daemon=True)
        self.thread.start()

    def read_output(self, websocket: WebSocket, loop: asyncio.AbstractEventLoop):
        while True:
            if self.channel and self.channel.recv_ready():
                try:
                    data = self.channel.recv(1024)
                    if not data:
                        break
                    message = data.decode("utf-8", errors="replace")
                    asyncio.run_coroutine_threadsafe(websocket.send_text(message), loop)
                except Exception as e:
                    print("Ошибка чтения из SSH-канала:", e)
            time.sleep(0.1)

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await websocket.accept()
    ssh_manager = SSHManager()
    try:
        ssh_manager.connect("localhost", 22, "admin", "admin")
    except Exception as e:
        await websocket.send_text(f"Ошибка подключения: {e}\n")
        await websocket.close()
        return

    loop = asyncio.get_running_loop()
    ssh_manager.start_reading(websocket, loop)

    try:
        while True:
            data = await websocket.receive_text()
            ssh_manager.send_command(data)
    except WebSocketDisconnect:
        print("WebSocket отключился")
    except Exception as e:
        print("Ошибка:", e)
    finally:
        if ssh_manager.client:
            ssh_manager.client.close()

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", host="127.0.0.1", port=8000, reload=True)
