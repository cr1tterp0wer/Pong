package com.stenqvi.pong;

import java.awt.Rectangle;

public class Ball extends CollisionObject {

	public boolean bIsDead=true;
	private int deltaX = 3;
	private int deltaY = 3;


	public Ball(int _x, int _y, int _height, int _width){
		super(_x,_y,_height,_width);

	}

	////////////////////////////////////////////////
	//pass the paddles
	///////////////////////////////////////////////
	public void update(Rectangle _p01, Rectangle _p02, boolean spacePressed)
	{


		if( bIsDead==false)
		{
			checkBorder(_p01,_p02);
		}
		else if (bIsDead)
			follow(_p01);

		if(spacePressed)
			bIsDead = false;
	}

	public void follow(Rectangle _rect)
	{

		this.rect.x  = (this.rect.x = _rect.x + this.rect.width+10);
		this.rect.y  =  ((_rect.height/2) +_rect.y) - this.rect.height/2;


	}


	public void checkBorder(Rectangle _p01, Rectangle _p02)
	{

		if(!bIsDead){
			this.rect.x+=deltaX;
			this.rect.y+=deltaY;
		}
		//800x600 playing board

		if (this.rect.x <=0)
		{
			bIsDead=true;
			follow(_p01);
		}

		if(this.rect.x >=775)
		{
			bIsDead=true;
			follow(_p01);
		}
		if(this.rect.y<=0)			
			deltaY=3;

		if(this.rect.y >= 548)
			deltaY =-3;
		if(this.rect.intersects(_p01))
			deltaX = 3;

		if(this.rect.intersects(_p02))	 
			deltaX =-3;


	}



}
