package com.devonfw.app.java.order.orderservice.dataaccess.api;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.devonfw.app.java.order.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.app.java.order.orderservice.common.api.Order;
import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;

/**
 * @author KBUKOWSK
 */
@Entity(name = "OrderSummary")
public class OrderEntity extends ApplicationPersistenceEntity implements Order {

  private OrderStatus status;

  private Double price;

  private LocalDateTime creationDate;

  private CustomerEntity owner;

  private Set<ItemEntity> orderPositions = new HashSet<>();

  private static final long serialVersionUID = 1L;

  /**
   * @return orderPositions
   */
  @ManyToMany
  @JoinTable(name = "OrderPosition", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id"))
  public Set<ItemEntity> getOrderPositions() {

    return this.orderPositions;
  }

  /**
   * @param orderPositions new value of {@link #getorderPositions}.
   */
  public void setOrderPositions(Set<ItemEntity> orderPositions) {

    this.orderPositions = orderPositions;
  }

  /**
   * @return status
   */
  @Override
  @Enumerated(EnumType.STRING)
  public OrderStatus getStatus() {

    return this.status;
  }

  /**
   * @param status new value of {@link #getstatus}.
   */
  @Override
  public void setStatus(OrderStatus status) {

    this.status = status;
  }

  /**
   * @return price
   */
  @Override
  public Double getPrice() {

    return this.price;
  }

  /**
   * @param price new value of {@link #getprice}.
   */
  @Override
  public void setPrice(Double price) {

    this.price = price;
  }

  /**
   * @return creationDate
   */
  @Override
  @Column(columnDefinition = "DATE")
  public LocalDateTime getCreationDate() {

    return this.creationDate;
  }

  /**
   * @param creationDate new value of {@link #getcreationDate}.
   */
  @Override
  public void setCreationDate(LocalDateTime creationDate) {

    this.creationDate = creationDate;
  }

  /**
   * @return owner
   */
  @ManyToOne
  @JoinColumn(name = "ownerId")
  public CustomerEntity getOwner() {

    return this.owner;
  }

  /**
   * @param owner new value of {@link #getowner}.
   */
  public void setOwner(CustomerEntity owner) {

    this.owner = owner;
  }

  @Override
  @Transient
  public Long getOwnerId() {

    if (this.owner == null) {
      return null;
    }
    return this.owner.getId();
  }

  @Override
  public void setOwnerId(Long ownerId) {

    if (ownerId == null) {
      this.owner = null;
    } else {
      CustomerEntity customerEntity = new CustomerEntity();
      customerEntity.setId(ownerId);
      this.owner = customerEntity;
    }
  }

}
