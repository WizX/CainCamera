package com.cgfay.filterlibrary.glfilter.mosaic;

import android.content.Context;
import android.opengl.GLES30;

import com.cgfay.filterlibrary.glfilter.base.GLImageFilter;
import com.cgfay.filterlibrary.glfilter.utils.OpenGLUtils;

/**
 * 三角形马赛克滤镜
 */
public class GLImageMosaicTriangleFilter extends GLImageFilter {

    private int mMosaicSizeHandle;
    private float mMosaicSize;

    public GLImageMosaicTriangleFilter(Context context) {
        this(context, VERTEX_SHADER, OpenGLUtils.getShaderFromAssets(context,
                "shader/mosaic/fragment_mosaic_triangle.glsl"));
    }

    public GLImageMosaicTriangleFilter(Context context, String vertexShader, String fragmentShader) {
        super(context, vertexShader, fragmentShader);
    }

    @Override
    public void initProgramHandle() {
        super.initProgramHandle();
        if (mProgramHandle != OpenGLUtils.GL_NOT_INIT) {
            mMosaicSizeHandle = GLES30.glGetUniformLocation(mProgramHandle, "mosaicSize");
            setMosaicSize(30.0f);
        }
    }

    @Override
    public void onDrawFrameBegin() {
        super.onDrawFrameBegin();
        GLES30.glUniform1f(mMosaicSizeHandle, mMosaicSize * (1.0f / Math.min(mImageWidth, mImageHeight)));
    }

    /**
     * 设置马赛克大小
     * @param size
     */
    public void setMosaicSize(float size) {
        mMosaicSize = size;
    }
}
