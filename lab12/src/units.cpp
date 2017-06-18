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


TEST_CASE( "Ring set Test", "[vector]" ) {

	Counter count1;
	count1.setRing(2);
	count1.setPosition(20);
	count1.work();

	REQUIRE(count1.getRing() == 2);
	REQUIRE(count1.getPosition() == 20);
 }

TEST_CASE( "Ring work Test", "[vector]" ) {

	Counter count1;
	count1.setRing(2);
	count1.setPosition(20);
	count1.work();

	REQUIRE(count1.getRing() == 2);
	REQUIRE(count1.getPosition() == 20);
	REQUIRE(count1.ring(18) == false);
	REQUIRE(count1.ring(2) == true);
 }
