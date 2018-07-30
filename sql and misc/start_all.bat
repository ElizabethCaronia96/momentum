
SET momentum-deployables="C:\Users\Administrator\Desktop\momentum\deployables"

start cmd /k "TITLE QUICKFIX & CD /D %momentum-deployables%\quickfixj\bin & executor.bat executor.cfg"

timeout /t 3

start cmd /k "TITLE ACTIVEMQ & CD /D %momentum-deployables%\apache-activemq-5.15.4-bin\apache-activemq-5.15.4\bin & activemq.bat start"

timeout /t 5

start cmd /k "TITLE ORDER-BROKER & CD /D %momentum-deployables%\OrderBrokerStandalone\OrderBroker & runBroker.bat"
