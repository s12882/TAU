/*
 * Counter.cpp
 *
 *  Created on: 18 èþí. 2017 ã.
 *      Author: Àíäðåé
 */

#include "Counter.hpp"
#define CATCH_CONFIG_MAIN
#include <iostream>
#include <conio.h>
#include "catch.hpp"


Counter::Counter(){

}

void Counter::setRing(int number){

	count.positionToRing = number;

}

int Counter::getRing(){
	return count.positionToRing;
}

void Counter::setPosition(int number){
	if (number < count.getRing()){
		std::cout << "Error - position should be grater" ;
	}else{
		count.currentPosition = number;
	}

}

int Counter::getPosition(){
	return count.currentPosition;
}

bool Counter::ring(int number){
	if(number == count.positionToRing){
		return true;
	}else{
		return false;
	}
}

void Counter::work(){

while(count.ring(count.currentPosition) != true){
	count.currentPosition--;
}
   std::cout <<"Ring at - " << count.currentPosition;
}


TEST_CASE( "Ring set Test", "[counter]" ) {

	Counter count1;
	count1.setRing(2);
	count1.setPosition(20);
	REQUIRE(count1.getPosition() == 20);
	count1.work();
	REQUIRE(count1.getPosition() == 2);
	

	
 }

TEST_CASE( "Ring work Test", "[counter]" ) {

	Counter count1;
	count1.setRing(2);
	count1.setPosition(20);
	
	REQUIRE(count1.getPosition() == 20);
	count1.work();
	REQUIRE(count1.getPosition() == 2);
	
	REQUIRE(count1.ring(18) == false);
	REQUIRE(count1.ring(2) == true);

	_getch();
 }
 
 SCENARIO("Countdown") {
    Counter count1;
    
	int start = 20;
	int end = 2;
	
	count1.setRing(end);
	count1.setPosition(start);
	
    GIVEN("Starting point at 'start'") {
        REQUIRE(count1.getPosition != NULL);
        WHEN("We start countdown") {
            REQUIRE_NOTHROW(count1.work());
            THEN("It should ring at 'end' value") {
				REQUIRE(count1.ring(2) == true);
            }
        }
    }

