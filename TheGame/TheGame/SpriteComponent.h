#pragma once
#include "ECS.h"
#include "Components.h"
#include <SDL.h>
#include "TextureLoader.h"
#include "Vector2D.h"

//https://github.com/carlbirch/BirchEngine/blob/master/BirchEngine/Src/ECS/SpriteComponent.h
class SpriteComponent : public Component
{
public:
	SpriteComponent() = default;

	SpriteComponent(const char* path) {
		setTexture(path);
	}

	void init() override {

		position = &entity->getComponent<MovementComponent>();

		srcRect.x = srcRect.y = 0;
		srcRect.w = position->width;
		srcRect.h = position->height;
		
	}

	void update() override {
		destRect.x = static_cast<int>(position->position.x);
		destRect.y = static_cast<int>(position->position.y);
		destRect.w = position->width * position->scale; 
		destRect.h = position->height * position->scale;
	}

	void draw() override {
		TextureLoader::Draw(texture, srcRect, destRect);
	}

	void setTexture(const char* path) {
		texture = TextureLoader::LoadTexture(path);
	}

	~SpriteComponent() {
		SDL_DestroyTexture(texture);
	}

private:
	MovementComponent *position;
	SDL_Texture *texture;
	SDL_Rect srcRect, destRect;

};
