require 'rails_helper'

RSpec.describe PricesController, type: :controller do

  it 'should list all prices of product' do
    @price = FactoryGirl.create(:price)

    get :index, params: {product_id: @price.product_id}
    @prices_list = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(@prices_list.size).to eq(1)
    expect(@prices_list[0]["amount"]).to eq(@price.amount.to_s)
  end

  it 'should list all prices of product fails when nothing found' do
    @product = FactoryGirl.create(:product)

    get :index, params: {product_id: @product.id}

    expect(response.status).to eq(404)
  end

  it 'should find price of product by id success' do
    @price = FactoryGirl.create(:price)

    get :show, params: {product_id: @price.product_id, id: @price.id}
    @price_json = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(@price_json["amount"]).to eq(@price.amount.to_s)
  end

  it 'should find price of product by id failure when not found' do
    @product = FactoryGirl.create(:product)

    get :show, params: {product_id: @product.id, id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create price success' do
    @product = FactoryGirl.create(:product)
    @price = FactoryGirl.attributes_for(:price)

    post :create, params: {product_id: @product.id, price: @price}
    @new_price_json = JSON.parse(response.body)

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/products\/\d\/prices\/\d/)
    expect(@new_price_json["amount"]).to eq(@price[:amount])
  end

  it 'should create price failure when product not found' do
    @price = FactoryGirl.attributes_for(:price)

    post :create, params: {product_id: 1, price: @price}

    expect(response.status).to eq(400)
  end

  it 'should create price failure when without amount' do
    @product = FactoryGirl.create(:product)
    @price = FactoryGirl.attributes_for(:price, :without_amount)

    post :create, params: {product_id: @product.id, price: @price}

    expect(response.status).to eq(400)
  end

end
