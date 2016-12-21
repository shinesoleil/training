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
    @order = Order.find_of_customer(params[:customer_id]).find_by(id: params[:id])

    if @order
      render json: @order.to_json(:include => :order_items), :status => 200
    else
      head 404
    end
  end

  def create
    @order_items = params[:order_items]

    @sum = 0
    @order_items.each {|order_item|
      @sum += order_item[:price].to_f * order_item[:quantity].to_i
    }

    @order = Order.new({total_price: @sum, customer_id: params[:customer_id]})
    if !@order.save
      head 400
      return
    end

    @order_items.each {|order_item|
      order_item[:order_id] = @order.id
      @oi = OrderItem.new(order_item.permit(:price, :quantity, :subtotal, :order_id, :product_id))
      if !@oi.save
        head 400
        return
      end
    }

    render json:@order, :status => 201, :location => @order.route_url
  end
end
