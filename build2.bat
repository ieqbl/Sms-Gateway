@echo off
setlocal

set MODS=mods
set LIB=lib\postgresql-42.7.7.jar
set GATEWAY_JAR=gateway.jar
set MAIN_MODULE=gateway
set MAIN_CLASS=gateway.Main

if not exist %MODS% mkdir %MODS%

echo Compiling all modules...

javac --module-source-path . --module-path %LIB% -d %MODS% ^
  sms.api\src\module-info.java ^
  sms.api\src\api\*.java ^
  sms.nexmo\src\module-info.java ^
  sms.nexmo\src\Ndrivers\*.java ^
  sms.twilio\src\module-info.java ^
  sms.twilio\src\Tdrivers\*.java ^
  gateway\src\module-info.java ^
  gateway\src\gateway\*.java

if errorlevel 1 (
    echo Compilation failed.
    exit /b 1
)

echo Compilation completed.

echo Creating gateway.jar...

jar --create --file %GATEWAY_JAR% --main-class %MAIN_CLASS% -C %MODS%\%MAIN_MODULE% .

echo Running the application...
java -p %MODS%;%LIB% -m %MAIN_MODULE%

endlocal
