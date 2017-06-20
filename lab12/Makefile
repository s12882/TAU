

all: build/counter.o

build/counter.o: src/Counter.cpp
	g++ -std=c++11 -c src/Counter.cpp -o build/counter.o

tests: unit
	./unit -r junit
	./unit -s

unit: build/CounterTest.o build/units.o build/counter.o
	g++ build/CounterTest.o build/units.o build/counter.o -o unit


build/units.o: src/tests/units.cpp
	g++ -std=c++11 -Isrc -Ithirdparty -c src/tests/units.cpp -o build/units.o

clean:
	rm -f build/*.o unit

