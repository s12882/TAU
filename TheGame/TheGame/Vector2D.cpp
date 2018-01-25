#include "Vector2D.h"

//https://github.com/carlbirch/BirchEngine/blob/master/BirchEngine/Src/Vector2D.cpp
Vector2D::Vector2D() {
	x = 0.0f;
	y = 0.0f;
}

Vector2D::Vector2D(float x, float y) {
	this->x = x;
	this->y = y;
}

Vector2D& Vector2D::Add(const Vector2D& vect) {

	this->x += vect.x;
	this->y += vect.y;
	return *this;
}

Vector2D& Vector2D::Sub(const Vector2D& vect) {

	this->x -= vect.x;
	this->y -= vect.y;
	return *this;
}

Vector2D& Vector2D::Mult(const Vector2D& vect) {

	this->x *= vect.x;
	this->y *= vect.y;
	return *this;
}

Vector2D& Vector2D::Divide(const Vector2D& vect) {

	this->x /= vect.x;
	this->y /= vect.y;
	return *this;
}

Vector2D& operator+(Vector2D& v1, const Vector2D& v2){

	return v1.Add(v2);
}

Vector2D& operator-(Vector2D& v1, const Vector2D& v2) {

	return v1.Sub(v2);
}

Vector2D& operator*(Vector2D& v1, const Vector2D& v2) {

	return v1.Mult(v2);
}

Vector2D& operator/(Vector2D& v1, const Vector2D& v2) {

	return v1.Divide(v2);
}

Vector2D& Vector2D::operator+=(const Vector2D& vec) {
	return this->Add(vec);
}

Vector2D& Vector2D::operator-=(const Vector2D& vec) {
	return this->Sub(vec);
}

Vector2D& Vector2D::operator*=(const Vector2D& vec) {
	return this->Mult(vec);
}

Vector2D& Vector2D::operator/=(const Vector2D& vec) {
	return this->Divide(vec);
}

Vector2D& Vector2D::operator*(const int& i) {
	this->x *= i;
	this->y *= i; 

	return *this;
}

std::ostream& operator<<(std::ostream& stream, const Vector2D& vec) {
	stream << "(" << vec.x << "," << vec.y << ")";
	return stream;
}