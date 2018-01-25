#pragma once
#include <iostream>

//https://github.com/carlbirch/BirchEngine/blob/master/BirchEngine/Src/Vector2D.h
class Vector2D
{

public:
	float x;
	float y;

	Vector2D();

	Vector2D(float x, float y);

	Vector2D& Add(const Vector2D& vect);
	Vector2D& Sub(const Vector2D& vect);
	Vector2D& Mult(const Vector2D& vect);
	Vector2D& Divide(const Vector2D& vect);

	friend Vector2D& operator+(Vector2D& v1, const Vector2D& v2);
	friend Vector2D& operator-(Vector2D& v1, const Vector2D& v2);
	friend Vector2D& operator*(Vector2D& v1, const Vector2D& v2);
	friend Vector2D& operator/(Vector2D& v1, const Vector2D& v2);

	Vector2D& operator+=(const Vector2D& vec);
	Vector2D& operator-=(const Vector2D& vec);
	Vector2D& operator*=(const Vector2D& vec);
	Vector2D& operator/=(const Vector2D& vec);

	Vector2D& operator*(const int& i);
	friend std::ostream& operator<<(std::ostream& stream, const Vector2D& vec);
};