
package methods;

import java.util.ArrayList;

import camo_calc.Function;

public class Kutta {


		private double y0;
		private double x0;
		private double h=0.1;
		private Function dydx;
		
		public Double[][] solve( Double[] initial,double h, double xMax) throws Exception
		{
			ArrayList<Double> ks = new ArrayList<Double>();
			ArrayList<Double> ys = new ArrayList<Double>();
			ArrayList<Double> xs = new ArrayList<Double>();
			ArrayList<Double> k1s = new ArrayList<Double>();
			ArrayList<Double> k2s = new ArrayList<Double>();
			ArrayList<Double> k3s = new ArrayList<Double>();
			ArrayList<Double> k4s = new ArrayList<Double>();
			double xN = initial[0];
			double yN = initial[1];
			int n=0;
			double k1N;
			double k2N;
			double k3N;
			double k4N;
			Double vals[];
			if(getDydx().getVars().size()==1)
			{
				if(getDydx().getVars().get(0)=='y')
				{
					vals = new Double[] {yN};
					k1N= getDydx().evaluate(vals);
					vals = new Double[] {yN*k1N*h/2};
					k2N= getDydx().evaluate(vals);
					vals = new Double[] {yN};
					k3N= getDydx().evaluate(vals);
					vals = new Double[] {yN};
					k4N= getDydx().evaluate(vals);
				}
				else
				{
					vals = new Double[] {xN};
					vals = new Double[] {yN};
					k1N= getDydx().evaluate(vals);
					vals = new Double[] {yN};
					k2N= getDydx().evaluate(vals);
					vals = new Double[] {yN};
					k3N= getDydx().evaluate(vals);
					vals = new Double[] {yN};
					k4N= getDydx().evaluate(vals);
				}
			}
			else
			{
				if(getDydx().getVars().get(0)=='y')
				{
					vals = new Double[] {yN,xN};
					k1N= getDydx().evaluate(vals);
					vals = new Double[] {yN+(h/2)*k1N,xN+(h/2)};
					k2N= getDydx().evaluate(vals);
					vals = new Double[] {yN+(h/2)*k2N,xN+(h/2)};
					k3N= getDydx().evaluate(vals);
					vals = new Double[] {yN+h*k3N,xN+h};
					k4N= getDydx().evaluate(vals);
				}
				else
				{
					vals = new Double[] {xN,yN};
					k1N= getDydx().evaluate(vals);
					vals = new Double[] {xN+(h/2),yN+(h/2)*k1N};
					k2N= getDydx().evaluate(vals);
					vals = new Double[] {xN+(h/2),yN+(h/2)*k2N};
					k3N= getDydx().evaluate(vals);
					vals = new Double[] {xN+h,yN+h*k3N};
					k4N= getDydx().evaluate(vals);
				}
			}
			

									
			double kN = (h/6)*(k1N+2*k2N+2*k3N+k4N);
			k1s.add(k1N);
			k2s.add(k2N);
			k3s.add(k3N);
			k4s.add(k4N);
			ks.add(kN);
			ys.add(yN);
			xs.add(xN);
			
			while(xN<xMax)
			{
				System.out.println(n);
				n++;
				yN= yN+kN;
				xN= xN + h;
				if(getDydx().getVars().size()==1)
				{
					if(getDydx().getVars().get(0)=='y')
					{
						vals = new Double[] {yN};
						k1N= getDydx().evaluate(vals);
						vals = new Double[] {yN*k1N*h/2};
						k2N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k3N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k4N= getDydx().evaluate(vals);
					}
					else
					{
						vals = new Double[] {xN};
						vals = new Double[] {yN};
						k1N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k2N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k3N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k4N= getDydx().evaluate(vals);
					}
				}
				else
				{
					if(getDydx().getVars().get(0)=='y')
					{
						vals = new Double[] {yN,xN};
						k1N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k2N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k3N= getDydx().evaluate(vals);
						vals = new Double[] {yN};
						k4N= getDydx().evaluate(vals);
					}
					else
					{
						vals = new Double[] {xN,yN};
						k1N= getDydx().evaluate(vals);
						vals = new Double[] {xN+(h/2),yN+(h/2)*k1N};
						k2N= getDydx().evaluate(vals);
						vals = new Double[] {xN+(h/2),yN+(h/2)*k2N};
						k3N= getDydx().evaluate(vals);
						vals = new Double[] {xN+h,yN+h*k3N};
						k4N= getDydx().evaluate(vals);
					}
				}
				kN = (h/6)*(k1N+2*k2N+2*k3N+k4N);
				k1s.add(k1N);
				k2s.add(k2N);
				k3s.add(k3N);
				k4s.add(k4N);
				ks.add(kN);
				ys.add(yN);
				xs.add(xN);
			}
			Double outputMatrix[][] = new Double[ys.size()][4];
			for(int r = 0 ; r < ys.size();r++ )
			{
				outputMatrix[r][0]=r+0.0;
				outputMatrix[r][1]=xs.get(r);
				outputMatrix[r][2]=ys.get(r);
				outputMatrix[r][3]=ks.get(r);
			}
			return outputMatrix;
		}

		public Function getDydx() {
			return dydx;
		}

		public void setDydx(Function dydx) {
			this.dydx = dydx;
		}
	}


