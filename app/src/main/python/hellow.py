import phonenumbers
#import folium
#from opencage.geocoder import OpenCageGeocode
from phonenumbers import carrier, geocoder, timezone




def main(xyz):
    mobileNo=phonenumbers.parse(xyz)
    print("\n")




#     Key = "e262d8a9fc864d4c85f5e7639c58c227"
#     yourLocation = geocoder.description_for_number(mobileNo,"en")
#     geocoder = OpenCageGeocode(Key)
#     query = str(yourLocation)
#     results = geocoder.geocode(query)
#
#     lat = results[0]['geometry']['lat']
#     lng = results[0]['geometry']['lng']
    #print("latitude:",lat)
    #print("longitude:",lng)
    return  "country:"+geocoder.description_for_number(mobileNo,"en")+"\nCarrier:"+carrier.name_for_number(mobileNo,"en")+"\nCountry code:"+str(mobileNo.country_code)+"\nValid:"+str(phonenumbers.is_valid_number(mobileNo))+"\npossibility:"+str(phonenumbers.is_possible_number(mobileNo))+"\nTime:"+str(timezone.time_zones_for_number(mobileNo))





