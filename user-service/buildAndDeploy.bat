:: This command file was created to automatization for building the project and deploying to Docker

@ECHO OFF

ECHO ===================================
ECHO Building project...
ECHO ===================================
CALL gradlew clean build -x test
ECHO:

ECHO ===================================
ECHO Building container...
ECHO ===================================
CALL docker-compose build
ECHO:

ECHO ===================================
ECHO Starting container...
ECHO ===================================
CALL docker-compose up
ECHO:

PAUSE