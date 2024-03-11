# Lights Out!

[Lights Out](https://en.wikipedia.org/wiki/Lights_Out_(game)) is a logic game featuring an N-by-N grid of lights. Each light can be in one of two possible states (*on* or *off*). Clicking on a light toggles its state as well as the state of all of its neighbours. The goal of the game is to bring all of the lights to the same state.

The application is implemented in Java, using the Swing framework to create the GUI.

## Installation

This project is built using [Java 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) and managed with Maven 3.9.6.

To build the application from source, navigate to the project directory and run
```sh
mvn clean package
```

Run the application using
```sh
java -jar target/lights-out-1.1.jar
```

## Contact

For any queries regarding the application, please contact Thomas Kong (txdk2009@gmail.com).