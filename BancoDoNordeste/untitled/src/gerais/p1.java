package gerais;

public class p1 {
    /*
    Given a set of points (x, y) on a 2D cartesian plane, find the two closest points.
    For example, given the points [(1, 1), (-1, -1), (3, 4), (6, 1), (-1, -6), (-4, -3)]
    , return [(-1, -1), (1, 1)]


     */
    public static float distance(float x1, float y1, float x2, float y2)
    {
        return Math.sqrt(Math.pow((x2)))
    }
  public static float distance(float [][] minDistance, float[][] points)
  {
      float min = 0;
      boolean [][] saw = new boolean[points.length][points.length];
      int index [][][] = new int[points.length][points.length][2];
      int point = 1 ;
      for(int i = 0; i < points.length; i++)
      {
          for(int j = i;j< points.length;j++)
          {
                if(i!= j)
                {
                    float P1x = points[i][0];
                    float P1y = points[i][1];
                    float P2x = points[j][0];
                    float P2y = points[j][1];
                    float distanceij = distance(p1x,p1y,p2x,p2y);

                    if(distanceij < min)
                    {
                        index[0][0][0] = (int) points[i][j][0];

                    }
                }
                point+=
          }

          float d = distance();
      }


  }

  public static void main(String[] args) {
        // definir entrada
        //[(1, 1), (-1, -1), (3, 4), (6, 1), (-1, -6), (-4, -3)]
        float [][] points ={
                {1,1},
                {-1,-1},
                {3,4},
                {6,1},
                {-1,-6},
                {-4,-3}

        };

        //criar função para calcular distância

        //definir um float para ser atualizado em cada cálculo de distância
            //definir array de 2 elementos para guaraddar os elemebtos

    }
}
