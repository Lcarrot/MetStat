# MetStat

MetStat - это приложение для отслеживания кликов пользователей на веб-страницах с использованием JavaScript, Spring Framework для бэкэнда и ClickHouse для хранения данных.

## Установка и настройка

### Backend (Java + Spring)

1. **Клонировать репозиторий:**

    ```bash
    git clone https://github.com/Lcarrot/MetStat.git
    ```

2. **Перейти в директорию:**

    ```bash
    cd backend/metstat
    ```

3. **Настроить ClickHouse:**

    - Убедитесь, что ClickHouse установлен и запущен. См. clickhouse-info

4. **Настроить приложение:**

    - Отредактируйте файл `application.properties`, установив параметры подключения к ClickHouse.

**Запустить приложение с использованием Maven:**

    ```bash
    mvn spring-boot:run
    ```

    Приложение будет доступно по адресу [http://localhost:8080](http://localhost:8080).

### Frontend (JavaScript)

1. **Клонировать репозиторий:**

    ```bash
    git clone https://github.com/Lcarrot/MetStat.git
    ```

2. **Перейти в директорию:**

    ```bash
    cd stat-script
    ```

3. **Открыть файл `index.html` в вашем веб-браузере или развернуть на веб-сервере.**

## Обзор проекта

MetStat позволяет отслеживать клики пользователей на веб-страницах и сохранять эту информацию в ClickHouse для последующего анализа.

### Backend (Java + Spring)

- **AddEventController.java:** Контроллер для обработки запросов, связанных с кликами.
- **EventRepository.java:** Репозиторий для взаимодействия с базой данных.
- **AddEvent.java:** Сущность, представляющая модель данных для кликов.


## Требования

- Java 17 или выше.
- Spring для сборки Java-приложения.
- ClickHouse, установленный и доступный для бэкэнда.
- Веб-браузер с поддержкой JavaScript для клиентской части.

