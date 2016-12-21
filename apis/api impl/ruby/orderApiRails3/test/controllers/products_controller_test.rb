require 'test_helper'

class ProductsControllerTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end
  test "all info" do
    get products_url

    assert_response :success
  end

  test "find by id" do
    product = products(:one)
    get product_url(product)

    assert_response :success
  end

  test "find by id fails" do
    get "/products/999"

    assert_response :missing
  end

  test "create with valid info" do
    product = products(:one)
    post products_url, params: {product: {name: product.name}}

    assert_response :created
    assert_match /products/, response.get_header("location")
  end

  test "create with invalid info" do
    post products_url, params: {product: {name: nil}}

    assert_equal 400, response.status
  end
end
