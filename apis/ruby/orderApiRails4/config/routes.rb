Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  resources :products, only: [:index, :show, :create, :update, :delete] do
    resources :prices, only: [:index, :show, :create]
  end

  resources :customers, only: [:index, :show, :create, :update, :delete]
end
