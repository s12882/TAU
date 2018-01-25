#pragma once
#include "ECS.h"
#include "Components.h"
#include "Vector2D.h"


class StaticComponent : public Component
{
public:

	Vector2D position;
	Vector2D velocity;

	int height = 32;
	int width = 32;
	int scale = 1;

	float xForce;
	float yForce;
	float impulse;
	float mass = 250.0f;
	float XresistanceConstant;
	float YresistanceConstant;

	double t = 0.0;
	float dt = 0.002f;


	StaticComponent() {
		position.x = 0.0f;
		position.y = 0.0f;
	}

	StaticComponent(float x, float y) {
		position.x = x,
			position.y = y;
	}

	StaticComponent(int sc) {
		position.x = 0.0f;
		position.y = 0.0f;
		scale = sc;
	}

	StaticComponent(float x, float y, int h, int w, int sc) {
		position.x = x,
		position.y = y;
		height = h;
		width = w;
		scale = sc;
	}

	void init() override {

		velocity.x = 0;
		velocity.y = 0;

		xForce = 0;
		yForce = 0;

		impulse = 2500000;
		XresistanceConstant = 0.005f;
		YresistanceConstant = 0.01f;
	}
	void update() override {
		position.x += velocity.x * dt;
		position.y += velocity.y * dt;
	}

};
