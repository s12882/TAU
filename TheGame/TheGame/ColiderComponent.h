#pragma once
#include "string"
#include "SDL.h"
#include "Components.h"

class ColiderComponent : public Component
{
public:

	SDL_Rect collider;
	std::string tag;

	MovementComponent * position;

	ColiderComponent(std::string newTag) {
		tag = newTag; 
	}

	void init() {
		if (!entity->hasComponent<MovementComponent>()) {
			entity->addComponent<MovementComponent>();
		}
		position = &entity->getComponent<MovementComponent>();
	}

	void update() {
		collider.x = static_cast<int>(position->position.x);
		collider.y = static_cast<int>(position->position.y);
		collider.w = position->width * position->scale;
		collider.h = position->height * position->scale;
	}


private:

};

