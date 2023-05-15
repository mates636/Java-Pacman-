/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: FieldView
 */

package project.view;

import project.MazePresenter;
import project.common.CommonField;
import project.common.CommonMazeObject;
import project.common.Observable;
import project.game.Maze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class FieldView extends JPanel implements Observable.Observer {
    private CommonField model;
    private List<ComponentView> objects;
    private MazePresenter presenter;

    public FieldView(CommonField model, MazePresenter presenter) {
        this.model = model;
        this.objects = new ArrayList();
        this.privUpdate();
        model.addObserver(this);
        this.presenter = presenter;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.objects.forEach((v) -> {
            v.paintComponent(g);
        });
    }

    private void privUpdate() {
            if (this.model.canMove() && !this.model.IsHereKey() && !this.model.IsHereEnding()) {
                this.setBackground(Color.white);
                if (!this.model.isEmpty()) {
                    CommonMazeObject o = this.model.get();
                    ComponentView v;
                    if (o.isPacman()) {
                        v = new PacmanView(this, this.model.get());

                    } else {
                        v = new GhostView(this, this.model.get());
                    }

                    this.objects.add(v);
                } else {
                    this.objects.clear();
                }
            }else if(this.model.canMove() && this.model.IsHereKey() && !this.model.IsHereEnding() && !this.model.KeyTaken()){
                Color gold = new Color(255, 215, 0);
                this.setBackground(gold);
                if (!this.model.isEmpty()) {
                    CommonMazeObject o = this.model.get();
                    ComponentView v;
                    if (o.isPacman()) {
                        v = new PacmanView(this, this.model.get());

                    } else {
                        v = new GhostView(this, this.model.get());
                    }

                    this.objects.add(v);
                } else {
                    this.objects.clear();
                }
            }else if(this.model.canMove() && !this.model.IsHereKey() && this.model.IsHereEnding()){
                Color brown = new Color(165, 42, 42);
                this.setBackground(brown);
                if (!this.model.isEmpty()) {
                    CommonMazeObject o = this.model.get();
                    ComponentView v;
                    if (o.isPacman()) {
                        v = new PacmanView(this, this.model.get());

                    } else {
                        v = new GhostView(this, this.model.get());
                    }

                    this.objects.add(v);
                } else {
                    this.objects.clear();
                }
            }else if(this.model.canMove() && this.model.IsHereKey() && !this.model.IsHereEnding() && this.model.KeyTaken()){
                this.setBackground(Color.white);
                if (!this.model.isEmpty()) {
                    CommonMazeObject o = this.model.get();
                    ComponentView v;
                    if (o.isPacman()) {
                        v = new PacmanView(this, this.model.get());

                    } else {
                        v = new GhostView(this, this.model.get());
                    }

                    this.objects.add(v);
                } else {
                    this.objects.clear();
                }
            }else{
                this.setBackground(Color.lightGray);
            }

            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

    public final void update(Observable field) {
        this.privUpdate();
    }

}
