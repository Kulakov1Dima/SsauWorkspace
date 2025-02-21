from fastapi import FastAPI, Query
from fastapi.middleware.cors import CORSMiddleware
from typing import List, Optional, Dict, Union
from pydantic import BaseModel
import uvicorn

app = FastAPI()

# Настройка CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Модели данных
class LabWork(BaseModel):
    id: int
    title: str
    status: str

class LabDetails(BaseModel):
    id: int
    title: str
    description: str  # Текст задания
    comment: str      # Комментарий преподавателя
    status: str
    progress: int

class SubjectInfo(BaseModel):
    name: str
    description: str
    teacher: str
    email: str
    photo: str
    labs: list[LabWork]

class TeacherResponse(BaseModel):
    name: str
    email: str
    photo: str

class Slot(BaseModel):
    time: str
    #subjects: List[Optional[Subject]]

class ScheduleResponse(BaseModel):
    year: str
    days: List[Dict[str, str]]
    slots: List[Slot]


# Данные
SCHEDULE = {
    "year": "2024/2025",
    "days": [
        {"name": "Понедельник", "date": "17.02.25"},
        {"name": "Вторник",    "date": "18.02.25"},
        {"name": "Среда",      "date": "19.02.25"},
        {"name": "Четверг",    "date": "20.02.25"},
        {"name": "Пятница",    "date": "21.02.25"},
        {"name": "Суббота",    "date": "22.02.25"}
    ],
    "slots": [
        {
            "time": "08:00 - 09:35",
            "subjects": [None] * 6  # Все дни пустые
        },
        {
            "time": "09:45 - 11:20",
            "subjects": [
                {
                    "name": "Технологии проектирования",
                    "teacher": "Мезенцева Екатерина",
                    "type": "Лекция",
                    "platform": "online",
                    "conference": "https://conference.example.com"
                },
                None, None, None,
                {
                    "name": "Использование Linux",
                    "teacher": "Кузьмишина Татьяна Михайловна",
                    "type": "Лабораторная"
                },
                None
            ]
        },
        {
            "time": "11:30 - 13:05",
            "subjects": [
                {
                    "name": "Параллельное программирование",
                    "teacher": "Востокин Сергей",
                    "type": "Практика",
                    "platform": "online",
                    "conference": "https://another.conf"
                },
                None, None, None,
                {
                    "name": "Разработка WEB-приложений",
                    "teacher": "Тарасов Артем",
                    "type": "Экзамен",
                    "room": "512-14"
                },
                None
            ]
        },
        {
            "time": "13:30 - 15:05",
            "subjects": [
                {
                    "name": "Разработка WEB-приложений",
                    "teacher": "Лёзин Илья",
                    "type": "Лекция",
                    "platform": "online"
                },
                None, None, None, None, None
            ]
        },
        {
            "time": "15:15 - 16:50",
            "subjects": [
                None, None, None, None,
                {
                    "name": "Использование Linux",
                    "teacher": "Кузьмишина Татьяна",
                    "type": "Лабораторная",
                    "room": "510-14"
                },
                None
            ]
        },
    ]
}

TEACHERS_DATA = {
    "Технологии проектирования": {
        "name": "Мезенцева Екатерина",
        "email": "mezenceva@example.com",
        "photo": "http://localhost:8000/static/mezenceva.jpg"
    },
    "Использование Linux": {
        "name": "Кузьмишина Татьяна Михайловна",
        "email": "kuzmishina@example.com",
        "photo": "http://localhost:8000/static/teacher.jpg"
    },
    "Параллельное программирование": {
        "name": "Востокин Сергей",
        "email": "vostokin@example.com",
        "photo": "http://localhost:8000/static/vostokin.jpg"
    }
}

LABS_DATA = {
    1: LabDetails(
        id=1,
        title="Лабораторная работа №1",
        description="Изучите основы Linux и выполните задания.",
        comment="Всё отлично! Исправьте формат отчёта.",
        status="Не сдано",
        progress=70,
    ),
    2: LabDetails(
        id=2,
        title="Лабораторная работа №2",
        description="Работа с файловой системой Linux.",
        comment="Нужно доработать раздел 'Выводы'.",
        status="В процессе",
        progress=30,
    ),
}

SUBJECTS_DATA = {
    "Использование Linux": {
        "name": "Использование Linux",
        "description": "Курс посвящен изучению основ работы в операционной системе Linux.",
        "teacher": "Кузьмишина Татьяна Михайловна",
        "email": "kuzmishina@example.com",
        "photo": "http://localhost:8000/static/teacher.jpg",
        "labs": [
            LabWork(id=1, title="Лабораторная работа №1", status="Не сдано"),
            LabWork(id=2, title="Лабораторная работа №2", status="Сдано"),
            LabWork(id=3, title="Лабораторная работа №3", status="В процессе"),
        ]
    },
    "Технологии проектирования": {
        "name": "Технологии проектирования",
        "description": "Курс посвящен современным технологиям проектирования программного обеспечения.",
        "teacher": "Мезенцева Екатерина",
        "email": "mezenceva@example.com",
        "photo": "http://localhost:8000/static/mezenceva.jpg",
        "labs": [
            LabWork(id=1, title="Лабораторная работа №1", status="Не сдано"),
            LabWork(id=2, title="Лабораторная работа №2", status="Сдано"),
        ]
    }
}

# Роуты
@app.get("/")
async def home():
    return "Добро пожаловать в API расписания!"

@app.get("/schedule")
def get_schedule():
    return SCHEDULE

@app.get(
    "/teacher",
    response_model=TeacherResponse,
    summary="Получить информацию о преподавателе",
    description="Возвращает имя, email и фото преподавателя по переданному названию предмета.",
    responses={
        200: {
            "description": "Информация о преподавателе найдена.",
            "content": {
                "application/json": {
                    "example": {
                        "name": "Мезенцева Екатерина",
                        "email": "mezenceva@example.com",
                        "photo": "http://localhost:8000/static/mezenceva.jpg"
                    }
                }
            }
        },
        404: {
            "description": "Преподаватель не найден.",
            "content": {
                "application/json": {
                    "example": {
                        "name": "Не найден",
                        "email": "",
                        "photo": ""
                    }
                }
            }
        }
    }
)
def get_teacher(subject: str = Query(..., title="Название предмета", description="Название предмета, по которому ищется преподаватель")):
    """
    Возвращает информацию о преподавателе по названию предмета.
    
    **Пример запроса**:
    ```
    GET /teacher?subject=Технологии проектирования
    ```
    """
    teacher = TEACHERS_DATA.get(subject, {"name": "Не найден", "email": "", "photo": ""})
    return teacher

@app.get(
    "/labs/{subject_name}",
    response_model=List[LabWork],
    summary="Получить список лабораторных работ",
    description="Возвращает список лабораторных работ по указанному предмету.",
    responses={
        200: {
            "description": "Успешный ответ с лабораторными работами",
            "content": {
                "application/json": {
                    "example": [
                        {"id": 1, "title": "Лабораторная работа №1", "status": "Не сдано"},
                        {"id": 2, "title": "Лабораторная работа №2", "status": "Сдано"},
                        {"id": 3, "title": "Лабораторная работа №3", "status": "В процессе"}
                    ]
                }
            }
        },
        404: {
            "description": "Предмет не найден",
            "content": {
                "application/json": {
                    "example": {"detail": "Лабораторные работы по данному предмету не найдены"}
                }
            }
        }
    }
)
@app.get("/labs/{subject_name}")
async def get_labs(subject_name: str):
    """
    **Описание:**  
    Возвращает список лабораторных работ по указанному предмету.  
    Если предмет не найден, сервер вернёт ошибку 404.

    **Пример запроса:**  
    ```
    GET /labs/Программирование
    ```

    **Пример ответа (успешный - 200 OK):**  
    ```json
    [
        {"id": 1, "title": "Лабораторная работа №1", "status": "Не сдано"},
        {"id": 2, "title": "Лабораторная работа №2", "status": "Сдано"},
        {"id": 3, "title": "Лабораторная работа №3", "status": "В процессе"}
    ]
    ```

    **Пример ответа (ошибка - 404 Not Found):**  
    ```json
    {
        "detail": "Лабораторные работы по данному предмету не найдены"
    }
    ```
    """
    subject = SUBJECTS_DATA.get(subject_name)
    if subject:
        return subject["labs"]
    return []

@app.get("/lab/{lab_id}")
async def get_lab_details(lab_id: int):
    lab = LABS_DATA.get(lab_id, {"error": "Лабораторная работа не найдена"})
    return lab

@app.get("/subject/{subject_name}")
async def get_subject_info(subject_name: str):
    subject = SUBJECTS_DATA.get(subject_name)
    if subject:
        return subject
    return {"error": "Предмет не найден"}

# Запуск сервера
if __name__ == "__main__":
    uvicorn.run("main:app", host="localhost", port=8000)