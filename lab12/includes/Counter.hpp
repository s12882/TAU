/*
 * Counter.hpp
 *
 *  Created on: 18 июн. 2017 г.
 *      Author: јндрей
 */

#ifndef COUNTER_HPP_
#define COUNTER_HPP_


class Counter
{
public:
	Counter();

	void setRing(int number);
	void setPosition(int number);
	int getPosition();
	int getRing();
	int currentPosition;
	int positionToRing;
	bool ring(int number);
	void work();

}count;


#endif /* COUNTER_HPP_ */
