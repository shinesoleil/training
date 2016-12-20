require 'rails_helper'
require 'rspec/active_model/mocks'

RSpec.describe ProductsController, type: :controller do
  describe "GET #index" do
    it 'should return 200 when get products' do
      def Product.all
        return ["test"]
      end

      get :index

      expect(response).to have_http_status(200)
    end

    it 'should return 404 when get products not found' do
      def Product.all
        return []
      end

      get :index

      puts response
      expect(response).to have_http_status(404)
    end
  end
  
  describe "POST #create" do
    it 'should create failure with invalid info' do
      post :create, {name: "firstProduct"}

      assert_equal 400, response.status
    end
  end
end
