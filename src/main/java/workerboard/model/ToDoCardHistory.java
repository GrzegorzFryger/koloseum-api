package workerboard.model;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import workerboard.model.enums.ToDoCardState;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ToDoCardHistory  {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "to_do_card_id")
    private ToDoCard card;
    private String title;
    private String text;

    @CreatedBy
    private String modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    @Enumerated(STRING)
    private ToDoCardState action;

    public ToDoCardHistory() {
    }

    public ToDoCardHistory(ToDoCard card, ToDoCardState action) {
        this.card = card;
        this.title = card.getTitle();
        this.text = card.getText();
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ToDoCard getCard() {
        return card;
    }

    public void setCard(ToDoCard card) {
        this.card = card;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ToDoCardState getAction() {
        return action;
    }

    public void setAction(ToDoCardState action) {
        this.action = action;
    }

}
