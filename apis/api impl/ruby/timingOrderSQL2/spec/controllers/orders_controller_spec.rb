require 'rails_helper'

RSpec.describe OrdersController, type: :controller do

  it 'should list all orders' do
    @order = FactoryGirl.create(:order)
    @order_item = FactoryGirl.create(:order_item, order: @order)

    get :index, params: {customer_id: @order.customer_id}
    @order_list = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(@order_list.size).to eq(1)
    expect(@order_list[0]["total_price"]).to eq(@order.total_price.to_s)
    # expect json embeds order_item
  end

  it 'should list all orders fails when nothing found' do
    @customer = FactoryGirl.create(:customer)

    get :index, params: {customer_id: @customer.id}

    expect(response.status).to eq(404)
  end

  it 'should list all orders fails when no customer' do
    get :index, params: {customer_id: 1}

    expect(response.status).to eq(404)
  end

  it 'should find order by id success' do
    @order = FactoryGirl.create(:order)
    @order_item = FactoryGirl.create(:order_item, order: @order)

    get :show, params: {customer_id: @order.customer_id, id: @order.id}
    @order_json = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(@order_json["total_price"]).to eq(@order.total_price.to_s)
  end

  it 'should find order by id failure when not found' do
    @customer = FactoryGirl.create(:customer)

    get :show, params: {customer_id: @customer.id, id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create order success' do
    @customer = FactoryGirl.create(:customer)
    @product = FactoryGirl.create(:product)
    @order_item = FactoryGirl.attributes_for(:order_item, product_id: @product.id)

    post :create, params: {customer_id: @customer.id, order_items: [@order_item]}

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/customers\/\d\/orders\/\d/)
  end
end
