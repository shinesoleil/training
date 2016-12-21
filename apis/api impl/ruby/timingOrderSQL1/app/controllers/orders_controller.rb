class OrdersController < ApplicationController
  def index
    @orders = Order.find_of_customer(params[:customer_id])

    if @orders.size != 0
      render json: @orders.to_json(:include => :order_items), :status => 200
    else
      head 404
    end
  end

  def show
    @order = Order.find_of_customer(params[:customer_id]).find_by(params[:id])

    if @order
      render json: @order.to_json(:include => :order_items), :status => 200
    else
      head 404
    end
  end

  def create
    @customer = Customer.find_by(id: params[:customer_id])

    if !@customer
      head 400
    end

    @order = Order.new(customer_id: params[:customer_id])
    @order.save

    @order_items = params[:order_items]

    @order_items.each {|order_item|
      order_item[:order_id] = @order.id
      oi = OrderItem.new(order_item.permit(:price, :quantity, :subtotal, :product_id, :order_id))
      oi.save
    }

    render json: @order.to_json(:include => :order_items), :status => 201
  end
end
