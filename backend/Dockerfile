FROM dockerreg.training.local:5000/java as build

WORKDIR /app

COPY . .

CMD ["/opt/itrs/netprobe/netprobe.linux_64", "$APPNAME", "-port", "7036", "-nopassword", "&"]

ENTRYPOINT ["java", "-jar", "/app/target/momentum-1.0-SNAPSHOT.jar"] 

