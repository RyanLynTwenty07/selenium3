for /f "tokens=2 delims=:" %%i in ('ipconfig ^| findstr /r "192.[0-9][0-9]*\.[0-9][0-9]*\.[0-9][0-9]*"') do set current_ip=%%i

java -jar selenium-server-4.11.0.jar hub --port 4444

pause