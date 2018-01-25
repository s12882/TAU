#pragma once
#include "ECS.h"
#include "Components.h"
#include "Vector2D.h"


class MovementComponent : public Component
{
public:

	World *worldForces;
	Vector2D position;
	Vector2D velocity;

	int height = 32;
	int width = 32;
	int scale = 1;

	float xForce;
	float yForce;
	float xAcceleration;
	float yAcceleration;
	float impulse;
	float mass = 250.0f;
	float XresistanceConstant;
	float YresistanceConstant;
	float dt = 0.002f;
	float strenght;

	bool isStatic;

	MovementComponent() {
		position.x = 0;
		position.y = 0;
	}

	//Constructor dla objektu dynamicznego
	MovementComponent(float x, float y, int h, int w, float str) {
		position.x = x, 
		position.y = y; 
		width = w;
		height = h;
		strenght = str;
	}

	//Constructor dla objektu statycznego
	MovementComponent(float x, float y, int h, int w, int sc, bool isSt) {
		position.x = x,
		position.y = y;
		height = h;
		width = w;
		scale = sc;
		isStatic = isSt;
	}

	void init() override {
	
		//Gravitacja objektu
		worldForces = &entity->getComponent<World>();
		worldForces->gravityForce = worldForces->gravityAcceleration * mass;

		velocity.x = 0;
		velocity.y = 0;

		xForce = 0;
		yForce = 0;

		//Object impulse / moc śilnika
		impulse = 2500000;
		XresistanceConstant = 0.005f;
		YresistanceConstant = 0.01f;
	}
	void update() override {

		//Jeżeli objekt nie jest statyczny
		if (!isStatic) {
			//Opór powietrza
			worldForces->XdragForce = XresistanceConstant * (velocity.x*velocity.x);
			worldForces->YdragForce = YresistanceConstant * (velocity.y*velocity.y);


			//Zmiana kierunku oporu
			if (velocity.x > 0) {
				worldForces->XdragForce = (worldForces->XdragForce * -1);
			}

			if (velocity.y > 0) {
				worldForces->YdragForce = (worldForces->YdragForce * -1);
			}

			//Zmiana pozycji
			position.x += velocity.x * dt;
			position.y += velocity.y * dt;

			//Przespieszenie
			velocity.y += ((yForce / mass) + worldForces->gravityForce + worldForces->YdragForce) * dt;
			velocity.x += ((xForce / mass) + worldForces->XdragForce) * dt;
		}	
	}

	void setImpulse(float newImpulse) {
		impulse = newImpulse;
	}

	//Zmiana pożiomu zdrowia
	void changeStrength(float value) {
		strenght += value;
	}


};
