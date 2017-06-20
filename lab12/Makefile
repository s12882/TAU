

all: build/vectorImpl.o

build/counter.o: src/Counter.cpp
	g++ -std=c++11 -c src/Counter.cpp -o build/vectorImpl.o

tests: unit
	./unit -r junit
	./unit -s

unit: build/VectorImplTest.o build/units.o build/vectorImpl.o
	g++ build/VectorImplTest.o build/units.o build/vectorImpl.o -o unit


build/units.o: src/tests/units.cpp
	g++ -std=c++11 -Isrc -Ithirdparty -c src/tests/units.cpp -o build/units.o

build/unit_sortlib.o: src/tests/VectorImplTest.cpp
	g++ -std=c++11 -Isrc -Ithirdparty  -c src/tests/VectorImplTest.cpp -o build/VectorImplTest.o

clean:
	rm -f build/*.o unit

