package com.stenqvi.pong;

import java.awt.Graphics;
import java.awt.Rectangle;

public class CollisionObject {

	protected Rectangle rect;
	
	public CollisionObject(int _x, int _y, int _height, int _width ){
		rect = new Rectangle(_x, _y, _height, _width);
	}
	
	public void update(int _index)
	{
		
		if(_index == 0 && rect.y > 0)
			rect.y-=3;

		else if (_index == 1 && rect.y < 600-(rect.height+30))
			rect.y +=3;		
		
		
	}
	
	public void draw(Graphics g)
	{
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public boolean isColliding(Rectangle _rect)
	{
		return this.rect.intersects(_rect);
	}
	
	public void setX(int _x){ rect.x=_x;}
	public void setY(int _y){ rect.y=_y;}
	public void setHeight(int _h){rect.height=_h;}
	public void setWidth(int  _w){rect.width=_w;}
	
	public int getX(){ return rect.x;}
	public int getY(){ return rect.y;}
	public int getHeight(){ return rect.height;}
	public int getWidth(){ return rect.width;}
	public Rectangle getRect(){ return rect;}
	
	
}
