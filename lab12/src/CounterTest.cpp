//============================================================================
// Name        : units.cpp
// Author      : Andrii Slobodian
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "Counter.hpp"

using namespace std;


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


 }
 
 SCENARIO("Countdown") {
    Counter count1;
    
	int start = 20;
	int end = 2;
	
	count1.setRing(end);
	count1.setPosition(start);
	
    GIVEN("Starting point at 'start'") {
        REQUIRE(count1.getPosition() != NULL);
        WHEN("We start countdown") {
            REQUIRE_NOTHROW(count1.work());
            THEN("It should ring at 'end' value") {
				REQUIRE(count1.ring(2) == true);
            }
        }
    }
 }
