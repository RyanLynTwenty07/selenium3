@echo ON

for /f "tokens=*" %%a in (config.ini) do (
    set ln=%%a
    for /f "tokens=1,2 delims==" %%b in ("%%a") do (
        set "%%b=%%c"
    )
)

java -Dwebdriver.edge.driver="../drivers/win/MicrosoftWebDriver.exe" -Dwebdriver.chrome.driver="C:\Users\linh.nguyen\.cache\selenium\chromedriver\win32\114.0.5735.90/chromedriver.exe" -jar selenium-server-4.11.0.jar node --hub http://%hub_ip%:4444/grid/register --port %port%

pause