FactoryGirl.define do
  factory :customer do
    name "MyString"
    city "MyString"

    trait :without_name do
      name nil
    end
  end
end
