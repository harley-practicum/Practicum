# Task Manager

Приложение для управления задачами, эпиками и подзадачами с возможностью отслеживания истории просмотра задач.

## Основные возможности

- Создание, редактирование и удаление задач (Task), эпиков (Epic) и подзадач (Subtask).
- Управление статусами задач: NEW, IN_PROGRESS, и DONE.
- Отслеживание истории просмотра задач (до 10 последних).
- Автоматическое обновление статуса эпика на основе статусов подзадач.

## Структура проекта

- `src/model/` — классы для описания модели задач (`Task`), эпиков (`Epic`), подзадач (`Subtask`) и статусов (`Status`).
- `src/service/` — классы для управления задачами (`TaskManager`), истории просмотра (`InMemoryHistoryManager`) и других вспомогательных функций.
- `src/Main.java` — точка входа в программу, содержит пример использования менеджера задач и истории.

## Установка и запуск

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
