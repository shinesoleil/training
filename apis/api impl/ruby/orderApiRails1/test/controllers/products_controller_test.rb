require 'test_helper'

class ProductsControllerTest < ActionDispatch::IntegrationTest
  test "return 200" do
    get '/products'
    assert_equal 200, response.status
    refute_empty response.body
  end

  test "return 200" do
    get '/'
  end
end
