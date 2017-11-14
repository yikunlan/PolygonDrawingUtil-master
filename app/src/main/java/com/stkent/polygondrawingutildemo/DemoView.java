/*
 * Copyright 2017 Stuart Kent
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.stkent.polygondrawingutildemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.stkent.polygondrawingutil.PolygonDrawingUtil;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class DemoView extends View {

    private final PolygonDrawingUtil polygonDrawingUtil = new PolygonDrawingUtil();
    private final Paint polygonFillPaint = new Paint(ANTI_ALIAS_FLAG);
    private final Paint polygonStrokePaint = new Paint(ANTI_ALIAS_FLAG);
    private final float strokeWidth = getResources().getDimension(R.dimen.stroke_width);

    private int numberOfSides = 3;
    private float cornerRadius = 120;
    private float rotation = 0;
    private float scale;
    private int fillColor = R.color.colorAccentTranslucent;
    private int strokeColor = R.color.colorAccent;
    private boolean isAllInvalidate = false;
    private Context mContext;
    public DemoView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        polygonFillPaint.setColor(ContextCompat.getColor(context, fillColor));
        polygonFillPaint.setStyle(Paint.Style.FILL);

        polygonStrokePaint.setColor(ContextCompat.getColor(context, strokeColor));
        polygonStrokePaint.setStrokeWidth(strokeWidth);
        polygonStrokePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        polygonFillPaint.setColor(ContextCompat.getColor(mContext, fillColor));
        polygonStrokePaint.setColor(ContextCompat.getColor(mContext, strokeColor));
        final float centerX = getWidth() / 2;
        final float centerY = getHeight() / 2;
        final float radius = scale * (getWidth() / 2 - strokeWidth);

        polygonDrawingUtil.drawPolygon(
                canvas,
                numberOfSides,
                centerX,
                centerY,
                radius,
                cornerRadius,
                rotation,
                polygonFillPaint);

        polygonDrawingUtil.drawPolygon(
                canvas,
                numberOfSides,
                centerX,
                centerY,
                radius,
                cornerRadius,
                rotation,
                polygonStrokePaint);
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void setNumberOfSides(final int numberOfSides) {
        this.numberOfSides = numberOfSides;
        if(!isAllInvalidate){
            invalidate();
        }

    }

    public void setCornerRadius(final float cornerRadius) {
        this.cornerRadius = cornerRadius;
        if(!isAllInvalidate){
            invalidate();
        }
    }

    public void setPolygonRotation(final float rotation) {
        this.rotation = rotation;
        if(!isAllInvalidate){
            invalidate();
        }
    }

    public void setScale(final float scale) {
        this.scale = scale;
        if(!isAllInvalidate){
            invalidate();
        }
    }

    public void setColor(final int fillColor,final int strokeColor){
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        if(!isAllInvalidate){
            invalidate();
        }
    }

}
