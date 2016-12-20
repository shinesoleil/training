require 'rails_helper'

RSpec.describe ProductsController, type: :controller do

  it 'should list all products' do
    product = FactoryGirl.create(:product)

    get :index
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res.size).to eq(1)
    expect(res[0]["name"]).to eq(product.name)
  end

  it 'should list all products fails when nothing found' do
    get :index

    expect(response.status).to eq(404)
  end

  it 'should find product by id success' do
    product = FactoryGirl.create(:product)

    get :show, params: {id: product.id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res["name"]).to eq(product.name)
  end

  it 'should find product by id failure when not found' do
    get :show, params: {id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create product success' do
    product = FactoryGirl.attributes_for(:product)
    post :create, params: {product: product}
    res = JSON.parse(response.body)

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/products\/\d/)
    expect(res["name"]).to eq(product[:name])
  end

  it 'should create product failure without name' do
    product = FactoryGirl.attributes_for(:product, :without_name)
    post :create, params: {product: product}

    expect(response.status).to eq(400)
  end
end

