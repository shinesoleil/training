class PaymentController < ApplicationController
  def index
    @payment = Payment.where(order_id: params[:order_id]).first

    if @payment
      render json: @payment, :status => 200
    else
      head 404
    end
  end

  def create
    params[:payment][:order_id] = params[:order_id]

    @payment = Payment.new(params.require(:payment).permit(:amount, :order_id))

    if @payment.save
      render json:@payment, :status => 201
    else
      head 400
    end
  end
end
