#pragma once

#include <cmath>
#include "Game.h"
#include "ECS.h"
#include "Components.h"
#include "Vector2D.h"

class World : public Component {
public:

	float gravityAcceleration;
	float gravityForce;
	float XdragForce;
	float YdragForce;
	double time;

	void init() override {
		gravityAcceleration = 9.8f;
		time = 30.0;
	 }

	void update() override {
		time -= 1.0 / 100.0;
	}

private:

};