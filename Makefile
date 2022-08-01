clean:
	./gradlew clean

build: clean
	./gradlew build

run:
	./gradlew bootRun

test:
	./gradlew test