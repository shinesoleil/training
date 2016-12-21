require 'test_helper'

class PricesControllerTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

  test "get all prices" do
    product = products(:one)
    get product_prices_url(product)
    assert_response :success
  end

  # product not found, prices not found are the same now
  test "get all prices fail" do
    get "/products/999/prices"
    assert_response :missing
  end

  test "get price by id" do
    product = products(:one)
    price = prices(:one)
    get product_price_url(product, price)

    assert_response :success
  end

  test "get price by id fails" do
    get "/products/1/prices/999"

    assert_response :missing
  end

  test "create price with valid info" do
    product = products(:one)
    price = prices(:one)
    post product_prices_url(product), params: {price: {amount: price.amount}}

    puts response.body
    assert_response :created
  end
end
