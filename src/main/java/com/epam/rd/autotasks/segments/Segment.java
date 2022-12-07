package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    // instance fields
    private final Point start;
    private final Point end;

    // Constructor
    public Segment(Point start, Point end) {
        if ( start.getX() == end.getX() && start.getY() == end.getY() ) throw new IllegalArgumentException();
        else {
            this.start = start;
            this.end = end;
        }
    }

    // Distance between 2 points
    double length() {
        double d = sqrt( pow( end.getX() - start.getX(), 2) + pow(start.getY() - end.getY(), 2) );
        return d;
    }

    // Middle point
    Point middle() {
        double x = ( start.getX() + end.getX() ) / 2.0;
        double y = ( start.getY() + end.getY() ) / 2.0;

        return new Point(x,y);
    }

    // The intersection point of the lines is found with the following value of t
    Point intersection(Segment another) {


        double denom = (this.start.getX() - this.end.getX()) * (another.start.getY() - another.end.getY()) - (this.start.getY() - this.end.getY()) * (another.start.getX() - another.end.getX()); // Checking if denominator is ZERO
        if (denom != 0.0) {
            double nomT = (this.start.getX() - another.start.getX()) * (another.start.getY() - another.end.getY()) - (this.start.getY() - another.start.getY()) * (another.start.getX() - another.end.getX());
            double nomU = (this.start.getX() - another.start.getX()) * (this.start.getY() - this.end.getY()) - (this.start.getY() - another.start.getY()) * (this.start.getX() - this.end.getX());
            double t = nomT / denom;
            double u = nomU / denom;

            if (t < 0 || t > 1.0)
                return null; //intersection outside the first segment

            if (u < 0 || u > 1.0)
                return null;  //intersection outside the second segment

            double col = (this.end.getY() - this.start.getY()) * (this.end.getX() - this.start.getX()) - (another.end.getX() - another.start.getX()) * (this.end.getY() - this.start.getY());
            if (abs(col) < 1.0e-10)  //better to compare abs(div) with small Eps
                return null;  //collinear case

            double Px = this.start.getX() + t*(this.end.getX() - this.start.getX());
            double Py = this.start.getY() + t*(this.end.getY() - this.start.getY());

            return new Point(Px, Py);
        }
        else return null; // If denominator is ZERO, return NULL
    }

}
