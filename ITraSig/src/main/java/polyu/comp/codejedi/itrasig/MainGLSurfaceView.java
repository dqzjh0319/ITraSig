package polyu.comp.codejedi.itrasig;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by CodeJedi on 13-10-30.
 */
public class MainGLSurfaceView extends GLSurfaceView{

    public MainGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        setRenderer(new MyGL20Render());

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
