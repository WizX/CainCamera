
# 根据API版本判断使用哪个版本的OpenGLES
if (${ANDROID_PLATFORM_LEVEL} LESS 12)
    message(FATAL_ERROR "OpenGL 2 is not supported before API level 11 (currently using ${ANDROID_PLATFORM_LEVEL}).")
    return()
elseif (${ANDROID_PLATFORM_LEVEL} LESS 18)
    add_definitions("-DDYNAMIC_ES3")
    set(GLES-lib GLESv2)
else ()
    set(GLES-lib GLESv3)
endif (${ANDROID_PLATFORM_LEVEL} LESS 11)

# 添加目录路径
include_directories( ./ )

# 添加源文件
add_library( gl_filter

        STATIC

        FrameBuffer.cpp

        # 公共类
        common/EglHelper.cpp
        common/OpenGLUtils.cpp
        common/CoordinateUtils.cpp
        common/vecmath.cpp

        # 基础滤镜
        filter/GLFilter.cpp
        filter/GLGroupFilter.cpp
        filter/GLGaussianBlurFilter.cpp
        filter/GLGaussianPassBlurFilter.cpp

        # 美颜滤镜
        filter/beauty/GLBeautyAdjustFilter.cpp
        filter/beauty/GLBeautyBlurFilter.cpp
        filter/beauty/GLBeautyFilter.cpp
        filter/beauty/GLBeautyHighPassFilter.cpp

        # 特效滤镜
        filter/effect/GLFrameBlackWhiteThreeFilter.cpp
        filter/effect/GLFrameTwoFilter.cpp
        filter/effect/GLFrameThreeFilter.cpp
        filter/effect/GLFrameFourFilter.cpp
        filter/effect/GLFrameSixFilter.cpp
        filter/effect/GLFrameNineFilter.cpp

        # 输入滤镜
        filter/input/GLInputFilter.cpp
        filter/input/GLInputABGRFilter.cpp
        filter/input/GLInputYUV420PFilter.cpp

        # 渲染结点
        node/DisplayRenderNode.cpp;
        node/InputRenderNode.cpp
        node/RenderNode.cpp
        node/RenderNodeList.cpp
        )

# 链接静态库
target_link_libraries( gl_filter

                       -landroid
                       -llog
                       -lEGL
                       ${GLES-lib} )

