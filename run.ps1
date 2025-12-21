cd "C:\Users\Mammas\Downloads\Sms-Gateway"

$process = Start-Process "C:\Program Files\Java\jdk-24\bin\java.exe" `
    -ArgumentList '--module-path "C:\Users\Mammas\Downloads\Sms-Gateway\gateway\lib;C:\Users\Mammas\Downloads\Sms-Gateway\out\production"', `
                   '--class-path "C:\Users\Mammas\Downloads\Sms-Gateway\out\production\gateway;C:\Users\Mammas\Downloads\Sms-Gateway\out\production\sms.api;C:\Users\Mammas\Downloads\Sms-Gateway\gateway\lib\*"', `
                   '-m gateway/gateway.Main' `
    -NoNewWindow -PassThru

Write-Host "wait"

Start-Sleep -Seconds 3

Start-Process "http://localhost:8080"

