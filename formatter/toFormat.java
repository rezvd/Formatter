EXAMPLE 1;

{aaa;{{aaa;}}}








EXAMPLE 2;


{{{{}}}}










EXAMPLE 3;

package main.it.sevenbits;public class Line {private Point start, end;public                         Line(Point start,                           Point end){
       








                                this.start = start;






        this.end = end;
   



 }

    public double getLength(){
        return Math.sqrt(Math.pow((end.getX() - start.getX()), 2) + Math.pow(end.getY() - start.getY(), 2));
    }public Point getStart









() {return start;
    }

}



