#pragma once
#include "Game.h"
#include "ECS.h"

class InputHandler : public Component
{
public:

	MovementComponent * position;
	const Uint8 *keystate;

	void  init() override {
		position = &entity->getComponent<MovementComponent>();
	}

	void update() override {
		//Możliwosć sprawdzania kilku przyczisków w ten samy czas
		if (Game::event.type == SDL_KEYDOWN) {

			if (Game::event.key.keysym.sym == SDLK_w) {
				position->yForce = -position->impulse;
			}

			if (Game::event.key.keysym.sym == SDLK_s) {
				position->yForce = position->impulse;
			}

			if (Game::event.key.keysym.sym == SDLK_a) {
				position->xForce = -position->impulse;
			}

			if (Game::event.key.keysym.sym == SDLK_d) {
				position->xForce = position->impulse;
			}
		}
		
			if (Game::event.type == SDL_KEYUP) {
				switch (Game::event.key.keysym.sym)
				{
				case SDLK_w:
					position->yForce = 0;
					break;
				case SDLK_s:
					position->yForce = 0;
					break;
				case SDLK_a:
					position->xForce = 0;
					break;
				case SDLK_d:
					position->xForce = 0;
					break;
				default:
					break;
				}
			}

		}

private:
	
};

