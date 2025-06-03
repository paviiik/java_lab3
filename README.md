##  Лабораторная №3

1.  Добавлен GET-эндпоинт `/prefixes/by-country-name?name=...`
    - Использует `@Query` к вложенной сущности `prefix.country.name`
2.  Внедрён кэш LFU в виде отдельного бина (`PrefixCache`)
    - Используется в `getById()` метода сервиса
    - Кэш обрабатывает до 10 объектов, отслеживает частоту доступа

SonarCloud
https: //sonarcloud.io/organizations/paviiik-1/projects
