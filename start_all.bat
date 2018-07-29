
SET momentum-deployables="C:\Users\dchen\Desktop\momentum-deployables"

start cmd /k "TITLE QUICKFIX & CD /D %momentum-deployables%\quickfixj\bin & executor.bat executor.cfg"

timeout /t 2

start cmd /k "TITLE ACTIVEMQ & CD /D %momentum-deployables%\apache-activemq-5.15.2-bin\apache-activemq-5.15.2\bin & activemq.bat start"

timeout /t 2

start cmd /k "TITLE ORDER-BROKER & CD /D %momentum-deployables%\OrderBrokerStandalone\OrderBroker & runBroker.bat"