package pl.edu.pwr.myapplication;


import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

public class SquareRenderer implements GLSurfaceView.Renderer{
    private Square shape;
    private static float anglePyramid = 0; // Rotational angle in degree for pyramid (NEW)
    private static float angleCube = 0;    // Rotational angle in degree for cube (NEW)
    private static float speedPyramid = 2.0f; // Rotational speed for pyramid (NEW)
    private static float speedCube = -1.5f;

    public SquareRenderer() {
        shape = new Square();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glLoadIdentity();                // Reset the model-view matrix
        gl.glTranslatef(-2.5f, 2.0f, -8.0f); // Translate right and into the screen
        gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
        gl.glRotatef(angleCube, 1.0f, 0, 0); // rotate about the axis (1,1,1) (NEW)
        gl.glRotatef(angleCube, 0, 0, 1.0f);
        shape.draw(gl);                      // Draw the cube (NEW)

        // Update the rotational angle after each refresh (NEW)
        anglePyramid += speedPyramid;   // (NEW)
        angleCube += speedCube;
        // shape.draw(gl);

        //gl.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);


        gl.glEnable(GL11.GL_NORMALIZE);

        gl.glMatrixMode(GL11.GL_PROJECTION);

        GLU.gluPerspective(gl, 105.0f, (float)width / (float)height, 0.1f, 50.0f);

        gl.glMatrixMode(GL11.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glDisable(GL11.GL_DITHER);

        gl.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);

        gl.glClearColor(0, 0, 0, 0);

        gl.glEnable(GL11.GL_CULL_FACE);
        gl.glFrontFace(GL11.GL_CCW);

        gl.glShadeModel(GL11.GL_SMOOTH);

        gl.glEnable(GL11.GL_DEPTH_TEST);
    }


}